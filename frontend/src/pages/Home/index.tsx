import { useEffect, useState } from "react";
import { getProps } from "../../api/service/HomeService";
import ColorName from "../../components/ColorName";
import SkillWriting from "../../components/SkillWriting";
import useWindowSize from "../../hooks/useWindowSize";
import IHomeProps from "../../types/props/IHomeProps";
import s from "./style.module.css";

function Home () {
    const defaultSize = { colorName: 70, skillWriting: 28 };
    const [colorNameSize, setColorNameSize] = useState(defaultSize.colorName);
    const [skillWritingSize, setSkillWritingSize] = useState(defaultSize.skillWriting);
    const size = useWindowSize();

    useEffect(() => {
        if (size.width <= 440) {
            setColorNameSize(Math.ceil(size.width / 7));
            setSkillWritingSize(Math.ceil(size.width / 15));
        } else {
            setColorNameSize(defaultSize.colorName);
            setSkillWritingSize(defaultSize.skillWriting);
        }
    }, [size]);

    // DATA
    const DEFAULT_DATA: IHomeProps = { name: ["", ""], skills: [""] };
    const [data, setData] = useState<IHomeProps>(DEFAULT_DATA);
    useEffect(() => {
        (async () => {
            setData(await getProps());
        })();
    }, []);

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