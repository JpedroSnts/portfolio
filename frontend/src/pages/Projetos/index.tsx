import useSWR from "swr";
import { getProjects } from "../../api/service/ProjectService";
import List from "../../components/List";
import Loader from "../../components/Loader";
import s from "./style.module.css";

function Projetos () {
    const { data, error } = useSWR("/projeto ", () => getProjects());

    if (error) return <div className={s.Content}><h3 style={{ color: "#c20a1f" }}>Ocorreu um erro ao carregar os dados!</h3></div>
    if (!data) return <div className={s.Content}><Loader /></div>

    return (
        <div className={s.Content}>
            <List projects={data} />
        </div>
    );
}

export default Projetos;