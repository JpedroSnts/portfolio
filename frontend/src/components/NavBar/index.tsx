import Link from 'next/link';
import { useRouter } from 'next/router';
import { useEffect, useState } from "react";
import changeHtmlBackground from "../../util/change-html-background";
import HamburgerMenu from "../HamburgerMenu";
import s from "./style.module.css";

interface IPage {
    title: String;
    url: String;
}
const pages: IPage[] = [
    { title: "Home", url: "/" },
    { title: "Sobre Mim", url: "/sobre-mim" },
    { title: "Projetos", url: "/projetos" },
    { title: "Certificados", url: "/certificados" },
    { title: "Contato", url: "/contato" }
]

function NavBar () {
    let itensMenu: JSX.Element[] = [];

    const location = useRouter();
    const [url, setUrl] = useState("/");
    const [isActive, setIsActive] = useState(false);
    const verifyUrl = (urlToVerify: String) => url === urlToVerify ? s.ActiveLink : s.None;

    pages.forEach((page, i) => (
        itensMenu.push(
            <li key={i}>
                <Link href={String(page.url)} className={verifyUrl(page.url)} onClick={() => setIsActive(false)}>{page.title}</Link>
            </li>
        )
    ));

    useEffect(() => {
        setUrl(location.pathname);
        changeHtmlBackground(location.pathname);
    }, [location]);


    return (
        <>
            <nav className={s.NavBar}>
                <Link href="/" className={s.Logo}>JpedroSnts</Link>
                <ul className={s.HorizontalMenu}>
                    {itensMenu}
                </ul>
                <div className={s.VerticalMenu}>
                    <div className={s.AreaToClose} style={{ display: `${isActive ? 'block' : 'none'}` }} onClick={() => setIsActive(false)}></div>
                    <HamburgerMenu color="#fff" isActive={isActive} onClick={() => setIsActive(!isActive)} />
                    <ul style={{ transform: `${isActive ? 'translateX(0)' : 'translateX(40vw)'}` }}>
                        {itensMenu}
                    </ul>
                </div>
            </nav>
            <div className={s.InsibleDivForNav} />
        </>
    )
}

export default NavBar;