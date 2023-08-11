# Typescript modules

Quick test with Custom ESM loaders

## Setup

    npm init
    npm install typescript --save-dev
    npx tsc --init
    npm install ts-node --save-dev

## Running

Works:

    nvm install 18
    npm run start_ts_node

Fails

    nvm install 20
    npm run start_ts_node

Works in all versions:

    npm run start