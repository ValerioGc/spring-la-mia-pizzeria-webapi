<template>
  <div class="home">
    <homePizza />
  </div>
</template>

<script>

  import homePizza from '@/components/homePizza.vue'

  export default {
    name: 'HomeView',
    components: {
      homePizza
    }
  }

</script>
