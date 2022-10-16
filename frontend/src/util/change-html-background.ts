export default function (pathname: String) {
	switch (pathname) {
		case "/":
			change("1.svg");
			break;
		case "/sobre-mim":
			change("2.svg");
			break;
		case "/projetos":
			change("3.svg");
			break;
		case "/certificados":
			change("4.svg");
			break;
		case "/contato":
			change("5.svg");
			break;
		default:
			change("1.svg");
	}
}

function change(image: String) {
	(document.querySelector("html") as HTMLHtmlElement).style.backgroundImage = `url("/bg/${image}")`;
}
