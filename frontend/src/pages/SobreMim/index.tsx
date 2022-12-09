import { useEffect, useState } from "react";
import useSWR from "swr";
import { getProps } from "../../api/service/AboutService";
import ColorName from "../../components/ColorName";
import Loader from "../../components/Loader";
import SkillWriting from "../../components/SkillWriting";
import useWindowSize from "../../hooks/useWindowSize";
import s from "./style.module.css";

function Sobre () {
    const defaultSize = { colorName: 29, skillWriting: 15 };
    const [colorNameSize, setColorNameSize] = useState(defaultSize.colorName);
    const [skillWritingSize, setSkillWritingSize] = useState(defaultSize.skillWriting);
    const size = useWindowSize();
    const { data, error } = useSWR("/template/sobre-mim", () => getProps());

    useEffect(() => {
        if (size.width <= 570) {
            setColorNameSize(24);
            setSkillWritingSize(15);
        } else {
            setColorNameSize(defaultSize.colorName);
            setSkillWritingSize(defaultSize.skillWriting);
        }
    }, [size]);

    if (error) return <div className={s.Content}><h3 style={{ color: "#c20a1f" }}>Ocorreu um erro ao carregar os dados!</h3></div>
    if (!data) return <div className={s.Content}><Loader /></div>

    return (
        <article className={s.Content}>
            <div className={s.About}>
                <img className={s.Photo} src={data.image} alt={`Foto de ${data.name[0]} ${data.name[1]}`} />
                <div className={s.Text}>
                    <ColorName name={data.name} fontSize={colorNameSize} />
                    <SkillWriting skills={data.skills} fontSize={skillWritingSize}></SkillWriting>
                    <div className="Line"></div>
                    <p>{data.text}</p>
                </div>
            </div>
        </article>
    );
}

export default Sobre;