<template>
  <div>
    <BlogList :word="$route.params.word" />
  </div>
</template>

<script>
import BlogList from './List';
export default {
  components: { BlogList },
};
</script>
