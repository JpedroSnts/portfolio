import { MouseEventHandler } from "react";

interface IHamburgerMenuProps {
    color: string;
    isActive: boolean;
    onClick: MouseEventHandler<HTMLButtonElement>;
}

function HamburgerMenu ({ color, isActive, onClick }: IHamburgerMenuProps) {
    const style: any = { '--color': color };

    return (
        <button
            className={`hamburger hamburger--squeeze js-hamburger ${isActive && "is-active"}`}
            onClick={onClick}
            type="button"
        >
            <span className="hamburger-box">
                <span className="hamburger-inner" style={style}></span>
            </span>
        </button>
    )
}

export default HamburgerMenu;