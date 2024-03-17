== ハンズオン
=== 概要
Controller・Service・Repository のインテグレーションテストを作成します。

=== 手順
. CatalogControllerIntegrationTest クラスの中身を見てみましょう。「/catalog/display-list」のリクエストに対しては、テストメソッドが作成済みです。「/catalog/display-details」のリクエストに対するテストメソッドを追加しましょう。

. OrderControllerIntegrationTest クラスの中身を見てみましょう。test_order メソッドは、OrderController クラスの order メソッドをテストするためのテストメソッドです。セッションスコープに格納する OrderSession オブジェクトは用意できていますが、MockMvc オブジェクトの perform メソッドの呼び出しや、アサーションが記述されていません。test_order メソッドを完成させましょう。アサーションでは以下を確認しましょう。
.. 発行された注文 ID を使って注文データを検索し、取得した注文データの顧客名や住所が期待通りになっている

=== オプション
. 在庫が足りないときに、期待する画面が表示されることを確認するテストメソッドを追加しましょう。
