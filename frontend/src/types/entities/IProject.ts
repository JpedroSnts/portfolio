import ITechnology from "./ITechnology";
import ITypeProject from "./ITypeProject";

export default interface IProject {
	id: string | number;
	name: string;
	description: string;
	link: string;
	image: string;
	type: ITypeProject;
	technologies: ITechnology[];
}
