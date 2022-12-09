import { useEffect, useState } from "react";
import useSWR from "swr";
import { getProps } from "../../api/service/HomeService";
import ColorName from "../../components/ColorName";
import Loader from "../../components/Loader";
import SkillWriting from "../../components/SkillWriting";
import useWindowSize from "../../hooks/useWindowSize";
import s from "./style.module.css";

function Home () {
    const defaultSize = { colorName: 70, skillWriting: 28 };
    const [colorNameSize, setColorNameSize] = useState(defaultSize.colorName);
    const [skillWritingSize, setSkillWritingSize] = useState(defaultSize.skillWriting);
    const size = useWindowSize();
    const { data, error } = useSWR("/template/home", () => getProps())

    useEffect(() => {
        if (size.width <= 440) {
            setColorNameSize(Math.ceil(size.width / 7));
            setSkillWritingSize(Math.ceil(size.width / 15));
        } else {
            setColorNameSize(defaultSize.colorName);
            setSkillWritingSize(defaultSize.skillWriting);
        }
    }, [size]);

    if (error) return <div className={s.Content}><h3 style={{ color: "#c20a1f" }}>Ocorreu um erro ao carregar os dados!</h3></div>
    if (!data) return <div className={s.Content}><Loader /></div>

    return (
        <div className={s.Content}>
            <article>
                <ColorName name={data.name} fontSize={colorNameSize} />
                <SkillWriting skills={data.skills} fontSize={skillWritingSize} />
            </article>
        </div>
    );
}

export default Home;