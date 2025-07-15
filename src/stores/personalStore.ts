// stores/personalStore.ts
import { defineStore } from 'pinia'
import type { PersonalFormDTO } from '@/models/PersonalFormDTO'

export const usePersonalStore = defineStore('personal', {
  state: () => ({
    formData: {
      birth_date: '',
      time: '',
      birth_place: '',
      language: ''
    } as PersonalFormDTO,
    result: null as any
  }),
  actions: {
    setFormData(payload: PersonalFormDTO) {
      this.formData = payload
    },
    setResult(data: any) {
      this.result = data
    }
  }
})
