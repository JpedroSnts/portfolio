import { useState } from "react";
import IProject from "../../../types/entities/IProject";
import Modal from "./Modal";
import s from "./style.module.css";

function ItemList ({ item }: { item: IProject }) {
    const [visible, setVisible] = useState(false);

    return (
        <>
            <Modal item={item} visible={visible} setVisible={setVisible} />
            <div className={s.Link} onClick={() => setVisible(!visible)}>
                <article className={s.ItemList}>
                    <div className={s.Image} style={{ backgroundImage: `url('${item.imagem}')` }}>
                        <section className={s.Description}>
                            <h1>{item.nome}</h1>
                            <small>{item.tipoProjeto.nome}</small>
                        </section>
                    </div>
                </article>
            </div>
        </>
    )
}

export default ItemList;