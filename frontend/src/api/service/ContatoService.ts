import IContactForms from "../../types/entities/IContactForms";
import IFormContact from "../../types/entities/IFormContact";
import { api } from "../api";

async function getContactForms(): Promise<IContactForms[]> {
	return (await api().get("/template/contato")).data;
}

async function sendEmail(form: IFormContact): Promise<any> {
	console.log(await api().post("/contato", form));
}

export { getContactForms, sendEmail };
