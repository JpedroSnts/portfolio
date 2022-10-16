import { useEffect, useState } from "react";
import { getProps } from "../../api/service/AboutService";
import ColorName from "../../components/ColorName";
import SkillWriting from "../../components/SkillWriting";
import useWindowSize from "../../hooks/useWindowSize";
import IAboutProps from "../../types/props/IAboutProps";
import s from "./style.module.css";

function Sobre () {
    const defaultSize = { colorName: 29, skillWriting: 15 };
    const [colorNameSize, setColorNameSize] = useState(defaultSize.colorName);
    const [skillWritingSize, setSkillWritingSize] = useState(defaultSize.skillWriting);
    const size = useWindowSize();

    useEffect(() => {
        if (size.width <= 570) {
            setColorNameSize(24);
            setSkillWritingSize(15);
        } else {
            setColorNameSize(defaultSize.colorName);
            setSkillWritingSize(defaultSize.skillWriting);
        }
    }, [size]);

    // DATA
    const DEFAULT_DATA: IAboutProps = { name: ["", ""], skills: [""], image: "", text: "" };
    const [data, setData] = useState<IAboutProps>(DEFAULT_DATA);
    useEffect(() => {
        (async () => {
            setData(await getProps());
        })();
    }, []);

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