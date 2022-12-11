import Head from "next/head";
import s from "../styles/pages/404.module.css";

export default function FourOhFour () {
    return (
        <>
            <Head>
                <title>Erro 404!</title>
            </Head>
            <div className={s.Content}>
                <h1> Página não encontrada </h1>
                <img style={{ marginLeft: "10px" }} src="/error.svg" alt="Ícone de Erro" />
            </div>
        </>
    )
}