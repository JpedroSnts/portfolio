import IProject from "../../types/entities/IProject";
import { api } from "../api";

async function getProjects(): Promise<IProject[]> {
	return (await api().get("/projeto")).data;
}

export { getProjects };
