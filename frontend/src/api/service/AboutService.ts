import IAboutProps from "../../types/props/IAboutProps";
import { api } from "../api";

async function getProps(): Promise<IAboutProps> {
	return (await api().get("/mock/about")).data;
}

export { getProps };
