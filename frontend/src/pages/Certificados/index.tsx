import useSWR from "swr";
import { getCertificados } from "../../api/service/CertificadosService";
import IconButton from "../../components/IconButton";
import Loader from "../../components/Loader";
import s from "./style.module.css";

function Home () {
    const { data, error } = useSWR("/certificado", () => getCertificados());

    if (error) return <div className={s.Content}><h3 style={{ color: "#c20a1f" }}>Ocorreu um erro ao carregar os dados!</h3></div>
    if (!data) return <div className={s.Content}><Loader /></div>

    return (
        <div className={s.Content}>
            <section className={s.Certificados}>
                {data.map(({ id, certificado, nome, tecnologia: { icone } }) => (
                    <IconButton icon={icone} text={nome} link={certificado} key={id} />
                ))}
            </section>
        </div>
    );
}

export default Home;