const ENDPOINT_PATH = "http://localhost:8080/";

export default {
   async getReservas(){
     //  const resp = await this.$axios.$get(ENDPOINT_PATH + "api");
     const ip = (await $axios.get(ENDPOINT_PATH + "api")).data
    }
};