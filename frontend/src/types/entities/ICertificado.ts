import ITechnology from "./ITechnology";

export default interface ICertificado {
	id: string | number;
	name: string;
	icon: string;
	link: string;
	technology: ITechnology;
}
