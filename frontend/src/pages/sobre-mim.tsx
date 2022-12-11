import { GetStaticProps } from "next";
import Head from "next/head";
import { useEffect, useState } from "react";
import { getProps } from "../api/service/AboutService";
import ColorName from "../components/ColorName";
import SkillWriting from "../components/SkillWriting";
import useWindowSize from "../hooks/useWindowSize";
import s from "../styles/pages/SobreMim.module.css";
import IAboutProps from "../types/props/IAboutProps";

export const getStaticProps: GetStaticProps = async () => {
    const data = await getProps();
    return { props: { data }, revalidate: 10 };
}

function Sobre ({ data }: { data: IAboutProps }) {
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

    return (
        <>
            <Head>
                <title>Sobre Mim</title>
            </Head>
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
        </>
    );
}

export default Sobre;