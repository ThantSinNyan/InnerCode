<template>
  <HeadImport />
  <body>
    <div class="main_header_wrapper">
      <NavLayoutContact />
      <NavLayoutMenu />
    </div>
    <router-view />
    <Footer />
  </body>
</template>
<script setup>
import HeadImport from '@/components/Layout/HeadImport.vue'
import NavLayoutContact from '@/components/Layout/NavLayoutContact.vue'
import NavLayoutMenu from '@/components/Layout/NavLayoutMenu.vue'
import Footer from '@/components/Layout/FooterPage.vue'
</script>
