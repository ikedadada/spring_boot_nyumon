== ハンズオン
=== 概要
更新系の REST API を作成し、挙動を確認します。

=== 手順
. ProductMaintenanceRestController クラスの中身を見てみましょう。参照系の REST API が提供されていますが、更新系は提供されていません。

. 更新系の、登録、更新、削除の REST API を追加してください。登録・更新用のデータは、json フォルダの中に用意しています。Curl コマンドで動作確認する際は、コマンドプロンプトを開いて「1802-shopping-rest-update」に移動しましょう。以下に、Curl コマンドのサンプルを示しますので、必要に応じて利用してください。

.. ID を指定してデータを参照

- ***
  ## curl -v -H "Accept: application/json" http://localhost:8080/api/products/p01

.. PUT でデータを更新

- ***
  ## curl -v -X PUT -H "Content-Type: application/json" http://localhost:8080/api/products/p01 -d @json/put-data.json

.. POST でデータを登録

- ***
  ## curl -v -X POST -H "Content-Type: application/json" http://localhost:8080/api/products -d @json/post-data.json

.. DELETE でデータを削除

- ***
  ## curl -v -X DELETE http://localhost:8080/api/products/p08

=== オプション
ID を指定してデータを取得しようとして、データが見つからなかった場合に、ステータスコード 404 を返すようにしましょう。
