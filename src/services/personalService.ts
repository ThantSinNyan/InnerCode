// services/personalService.ts
import axios from 'axios'
import { usePersonalStore } from '@/stores/personalStore'
import type { PersonalFormDTO } from '@/models/PersonalFormDTO'

export async function fetchPersonalOverview() {
  const store = usePersonalStore()
  const payload: PersonalFormDTO = store.formData

  try {
    const response = await axios.post('http://localhost:8080/api/users/personal-inside-data-overview', payload)
    store.setResult(response.data)
  } catch (error: any) {
    store.setResult(error.response?.data || 'Request failed')
    console.error('API error:', error)
  }
}
