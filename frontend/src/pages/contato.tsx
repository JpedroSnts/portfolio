import { GetStaticProps } from "next";
import Head from "next/head";
import { useState } from "react";
import { toast } from "react-toastify";
import { getContactForms, sendEmail } from "../api/service/ContatoService";
import Button from "../components/Form/Button";
import Input from "../components/Form/Input";
import IconButton from "../components/IconButton";
import s from "../styles/pages/Contato.module.css";
import IContactForms from "../types/entities/IContactForms";
import IFormContact from "../types/entities/IFormContact";

export const getStaticProps: GetStaticProps = async () => {
    const data = await getContactForms();
    return { props: { data }, revalidate: 10 };
}

function Contato ({ data }: { data: IContactForms[] }) {
    const INITIAL_STATE_FORM = { nome: "", email: "", mensagem: "" };
    const [form, setForm] = useState<IFormContact>(INITIAL_STATE_FORM);

    async function sendForm (e: React.FormEvent<HTMLFormElement>) {
        e.preventDefault();
        toast.dismiss();
        if (form.email.trim() === "") {
            toast.error("Email é obrigatório!");
            return;
        }
        if (form.nome.trim() === "") {
            toast.error("Nome é obrigatório!");
            return;
        }
        if (form.mensagem.trim() === "") {
            toast.error("Mensagem é obrigatória!");
            return;
        }
        try {
            await sendEmail(form);
        } catch (err: any) {
            toast.dismiss();
            const errors = err.response.data.errors;
            for (let i = 0; i < errors.length; i++) {
                toast.error(errors[i].defaultMessage);
            }
            return;
        }
        setForm(INITIAL_STATE_FORM);
        toast.success("E-mail enviado com sucesso!");
    }

    return (
        <>
            <Head>
                <title>Contato</title>
            </Head>
            <div className={s.Content}>
                <section className={s.Buttons}>
                    {data.map(({ icon, link, name }, i) => (
                        <IconButton
                            key={i}
                            icon={icon}
                            text={name}
                            link={link}
                        />
                    ))}
                </section>
                <form className={s.Form} onSubmit={sendForm}>
                    <div className={s.TopForm}>
                        <Input placeholder="Email" type="email" onChange={(e) => setForm({ ...form, email: e.target.value })} value={form.email} />
                        <Input placeholder="Nome" type="text" onChange={(e) => setForm({ ...form, nome: e.target.value })} value={form.nome} />
                    </div>
                    <div className={s.Message}>
                        <Input placeholder="Mensagem" type="textarea" onChange={(e) => setForm({ ...form, mensagem: e.target.value })} value={form.mensagem} />
                    </div>
                    <div>
                        <Button>Enviar</Button>
                    </div>
                </form>
            </div>
        </>
    );
}

export default Contato;