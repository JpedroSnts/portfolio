import type { AppProps } from 'next/app';
import Layout from '../components/Layout';
import "../styles/global/hamburgers.css";
import '../styles/global/index.css';

export default function App ({ Component, pageProps }: AppProps) {
  return <Layout><Component {...pageProps} /></Layout>
}
