import { useEffect, useState } from "react";
import { getProjects } from "../../api/service/ProjectService";
import List from "../../components/List";
import IProject from "../../types/entities/IProject";
import s from "./style.module.css";

function Projetos () {

    // DATA
    const [data, setData] = useState<IProject[]>([]);
    useEffect(() => {
        (async () => {
            setData(await getProjects());
        })();
    }, []);

    return (
        <div className={s.Content}>
            <List projects={data} />
        </div>
    );
}

export default Projetos;