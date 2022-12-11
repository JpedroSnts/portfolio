import axios from "axios";

function api() {
	return axios.create({
		baseURL: process.env.NEXT_PUBLIC_URL,
	});
}

export { api };
