import { GetStaticProps } from "next";
import Head from "next/head";
import { getProjects } from "../api/service/ProjectService";
import List from "../components/List";
import s from "../styles/pages/Projetos.module.css";
import IProject from "../types/entities/IProject";

export const getStaticProps: GetStaticProps = async () => {
    const data = await getProjects();
    return { props: { data }, revalidate: 10 };
}

function Projetos ({ data }: { data: IProject[] }) {

    return (
        <>
            <Head>
                <title>Projetos</title>
            </Head>
            <div className={s.Content}>
                <List projects={data} />
            </div>
        </>
    );
}

export default Projetos;