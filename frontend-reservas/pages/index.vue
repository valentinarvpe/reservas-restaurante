<template>
  <v-carousel height="500" interval="2600" show-arrows cycle hide-delimiter-background>
    <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src" cover>
      <div class="d-flex flex-column fill-height align-center text-white">
        <v-spacer></v-spacer>
        <DialogoComponent :="dialog" />
      </div>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      name: 'IndexPage',
      items: [
        {
          src: require('@/assets/images/w1.jpg')
        },
        {
          src: require('@/assets/images/w2.jpg')
        },
        {
          src: require('@/assets/images/w3.jpg')
        },
        {
          src: require('@/assets/images/w4.jpg')
        },
        {
          src: require('@/assets/images/w5.jpg')
        }
      ],
    }
  },
  methods: {
    abrirModal() {
      this.dialog = true;
    }
  }
}
</script>
