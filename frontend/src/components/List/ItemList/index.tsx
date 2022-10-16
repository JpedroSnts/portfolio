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
                    <section className={s.Description}>
                        <h1>{item.name}</h1>
                        <small>{item.type.name}</small>
                    </section>
                    <img src={item.image} alt={item.description} className={s.Image} />
                </article>
            </div>
        </>
    )
}

export default ItemList;