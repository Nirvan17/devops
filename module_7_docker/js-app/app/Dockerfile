FROM node:21-alpine

ENV MANGO_DB_USERNAME=admin \
    MANGO_DB_PWD=password

RUN mkdir -p /home/app

COPY ./app /home/app

WORKDIR /home/app

RUN npm install -y

CMD ["node", "server.js"]