import { GetStaticProps } from "next";
import Head from "next/head";
import { getCertificados } from "../api/service/CertificadosService";
import IconButton from "../components/IconButton";
import s from "../styles/pages/Certificados.module.css";
import ICertificado from "../types/entities/ICertificado";

export const getStaticProps: GetStaticProps = async () => {
    const data = await getCertificados();
    return { props: { data }, revalidate: 10 };
}

function Home ({ data }: { data: ICertificado[] }) {

    return (
        <>
            <Head>
                <title>Certificados</title>
            </Head>
            <div className={s.Content}>
                <section className={s.Certificados}>
                    {data.map(({ id, certificado, nome, tecnologia: { icone } }) => (
                        <IconButton icon={icone} text={nome} link={certificado} key={id} />
                    ))}
                </section>
            </div>
        </>
    );
}

export default Home;