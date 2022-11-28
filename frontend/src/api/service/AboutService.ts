import IAboutProps from "../../types/props/IAboutProps";
import { api } from "../api";

async function getProps(): Promise<IAboutProps> {
	return (await api().get("/template/sobre-mim")).data;
}

export { getProps };
