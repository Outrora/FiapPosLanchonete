import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 10000, // Número de usuários virtuais
    duration: '30s', // Duração do teste
};

export default function () {
    http.get('http://a70ea1343a77e45deb0f1738e14c9bfc-162678249.us-east-1.elb.amazonaws.com/cozinha/ListaPedidos');
    sleep(1);
}