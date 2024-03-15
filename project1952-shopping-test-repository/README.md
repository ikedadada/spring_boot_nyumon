== ハンズオン
=== 概要
Repository のユニットテストを作成します。

=== 手順
. JdbcProductRepository クラスのテストクラスを作成しましょう。テスト用の SQL ファイルは、src/test/resources 配下の JdbcProductRepositoryTest.sql に用意されています。JdbcProductRepository クラスの以下のメソッドをテストしてください。
.. selectAll メソッド
... 最低限、取得件数が期待通りになっていることを確認しましょう。
.. selectById メソッド
... 適当な ID で検索し、Product オブジェクトの中身が期待通りか確認しましょう。
.. update メソッド
... 適当な ID のデータを更新し、t_product テーブルのレコードが期待通りに変更されたことを確認しましょう。

=== オプション
. JdbcOrderRepository クラスのテストクラスを作成しましょう。
