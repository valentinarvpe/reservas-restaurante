const ENDPOINT_PATH = "http://localhost:8080/";

export default {
  register(email, password) {
    const user = { email, password };
    return this.$axios.$post(ENDPOINT_PATH + "login", user);
  },
};