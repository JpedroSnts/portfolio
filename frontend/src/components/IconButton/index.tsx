import s from "./style.module.css";

interface IIconButtonProps {
    icon?: string;
    text: string;
    link?: string;
    bgColor?: string;
    fontColor?: string;
}

function IconButton ({ icon, text, link, fontColor, bgColor }: IIconButtonProps) {
    const defaultColor = {
        text: `${fontColor ? fontColor : '#ffffff'}`,
        bg: `${bgColor ? bgColor : '#00000080'}`
    }

    if (link) {
        return (
            <a href={link} target="_blank" className={s.IconButton} style={{ backgroundColor: defaultColor.bg }} title={text}>
                {icon && (<img src={icon} />)}
                <span style={{ color: defaultColor.text }}>
                    {text}
                </span>
            </a>
        )
    }
    return (
        <button className={s.IconButton} style={{ backgroundColor: defaultColor.bg }} title={text}>
            {icon && (<img src={icon} />)}
            <span style={{ color: defaultColor.text }}>
                {text}
            </span>
        </button>
    )
}

export default IconButton;