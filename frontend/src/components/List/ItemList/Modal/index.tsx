import IProject from "../../../../types/entities/IProject";
import Button from "../../../Form/Button";
import s from "./style.module.css";

function Modal ({ item, visible, setVisible }: { item: IProject, visible: boolean, setVisible: (visible: boolean) => void }) {
    return (
        <div
            className={s.BackgroundBlur}
            style={{
                opacity: `${visible ? 1 : 0}`,
                visibility: `${visible ? 'visible' : 'hidden'}`
            }}>
            <div className={s.Area}>
                <div className={s.BtnClose}>
                    <span className={s.Close} onClick={() => setVisible(false)}></span>
                </div>
                <div className={s.Modal}>
                    <article className={s.AreaModal}>
                        <img src={item.imagem} alt={item.descricao} />
                        <section className={s.Description}>
                            <h1>{item.nome}</h1>
                            <small>{item.tipoProjeto.nome}</small>
                        </section>
                        <div>
                            <p>{item.descricao}</p>
                        </div>
                        <a href={item.link} target="_blank"><Button>Abrir</Button></a>
                    </article>
                </div>
            </div>
        </div>
    );
}

export default Modal;