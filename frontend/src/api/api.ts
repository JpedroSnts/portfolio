import axios from "axios";

function api() {
	return axios.create({
		baseURL: import.meta.env.VITE_API_URL,
	});
}

export { api };
