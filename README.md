# Weelink
___
Lightweight self-hosted link shortener based on Java

## Install
___
Clone the repository
```shell
git clone git@github.com:Marchosiax/weelink.git && cd weelink
```
Create `.env` file in order to define required environment variables 
```shell
vi .env
```
Copy these variables and paste them into the `.env` file and change
the values to whatever you like
```shell
DB_USER=root # Database username 
DB_PASS=root # Database password
DOMAIN=https://weel.ink # Your domain; Links will be created with this domain
ALIAS_LENGTH=5 # Length of shortened links
```
Build using docker compose
```shell
docker compose build
```
And finally run the project
```shell
docker compose up -d
```
## License
___
```
MIT License

Copyright (c) 2022 Marchosiax, Inc

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```