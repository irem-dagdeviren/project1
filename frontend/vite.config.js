import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { fileURLToPath, URL} from "node:url"

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/dev/v1/auth': {
        target: 'http://localhost:9099',
        changeOrigin: true,
        secure: false,
        ws: true
      },
      '/dev/v1/user-profile': {
        target: 'http://localhost:9091',
        changeOrigin: true,
        secure: false,
        ws: true
      }
    }
  },
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url ))
    }
  }
})
