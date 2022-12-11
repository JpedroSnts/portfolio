import { GetStaticProps } from 'next';
import Head from "next/head";
import { useEffect, useState } from "react";
import { getProps } from "../api/service/HomeService";
import ColorName from "../components/ColorName";
import SkillWriting from "../components/SkillWriting";
import useWindowSize from "../hooks/useWindowSize";
import s from "../styles/pages/Home.module.css";
import IHomeProps from "../types/props/IHomeProps";

export const getStaticProps: GetStaticProps = async () => {
  const data = await getProps();
  return { props: { data: { name: data.name, skills: data.skills } }, revalidate: 10 };
}

function Home ({ data }: { data: IHomeProps }) {
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

  return (
    <>
      <Head>
        <title>Home</title>
      </Head>
      <div className={s.Content}>
        <article>
          <ColorName name={data.name} fontSize={colorNameSize} />
          <SkillWriting skills={data.skills} fontSize={skillWritingSize} />
        </article>
      </div>
    </>
  );
}

export default Home;