FROM node:lts-alpine
WORKDIR /usr/src/app
COPY . .
RUN npm install
RUN npm run build
# Install simple http server for serving static contents.
RUN npm install -g http-server
CMD http-server dist