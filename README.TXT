Создание базы данных, таблиц, пользователей - на стороне сервера.
Создание индекса и маппинг - на стороне сервера.

Что можно доработать:
1. Валидация средствами spring
2. Дизайн
3. Проверка на наличие индекса, програмнное создание индекса и маппинг.
4. Сделать транзакции.
Mapping с ошибками:
curl -X PUT "localhost:9200/quote?pretty" { "mappings": {"quotedto":{ "properties": { "id": { "type": "long" }, "authorNickName" : { "type": "text" }, "content": { "type": "text" }, "date" : { "type": "date" } } } } }
curl -X PUT "localhost:9200/my_index" -H 'Content-Type: application/json' -d' {  "mappings": {    "_doc": {      "properties": {        "date": {          "type":   "date",          "format": "yyyy-MM-dd"        }      }  }  }}'
curl -XPUT "http://localhost:9200/quote/quotedto/_mapping?pretty" -H "Content-Type: application/json" -d "{ "quotedto" : { "properties" : { "id": { "type": "long" }, "authorNickName" : { "type": "text" }, "content": { "type": "text" }, "date" : { "type": "date" } } } }"
вроде сработало:
 curl -XPUT "http://localhost:9200/quote/quotedto/_mapping?pretty" -H "Content-Type: application/json" -d "{ \"quotedto\" : { \"properties\" : { \"id\": { \"type\": \"long\" }, \"authorNickName\" : { \"type\": \"text\" }, \"content\": { \"type\": \"text\" }, \"date\" : { \"type\": \"date\" } } } }"
  curl -XGET "localhost:9200/quote/_search?pretty"
  curl -XPUT "http://localhost:9200/quote/quotedto/_mapping?pretty" -H "Content-Type: application/json" -d "{ \"quotedto\" : { \"properties\" : { \"id\": { \"type\": \"long\" }, \"authorNickName\" : { \"type\": \"text\" }, \"content\": { \"type\": \"text\" }, \"date\" : { \"type\": \"date\", \"format\": \"yyyy-MM-dd\" } } } }"