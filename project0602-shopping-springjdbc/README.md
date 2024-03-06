== ハンズオン

=== 概要
JdbcTemplate クラスを使用していないプログラムを、JdbcTemplate クラスを使うように修正します。

=== 手順
. TrainingApplication クラスを実行してみましょう。「No qualifying bean of type 'org.springframework.jdbc.core.JdbcTemplate' available」というエラーが発生します。何が原因でしょうか？

. 必要な対応を行って、もう一度 TrainingApplication クラスを実行してみましょう。上手く動くはずです。

. JdbcOrderItemRepository クラスの中を確認してください。JdbcTemplate クラスを使用していません。JdbcTemplate クラスを使用するように修正して、TrainingApplication クラスを実行し、上手く動くようにしましょう。

. JdbcProductRepository クラスも JdbcTemplate クラスを使用していません。JdbcTemplate クラスを使用するように修正して、TrainingApplication クラスを実行し、上手く動くようにしましょう。

=== オプション
. JdbcOrderRepository クラスに、ID で検索して Order オブジェクトを取得するメソッドを追加しましょう。追加できたら、TrainingApplication クラスの main メソッド内で注文確定が完了した後に、JdbcOrderRepository クラスの Bean を DI コンテナから取得して、追加したメソッドを使って登録された注文データが取得できるか確認しましょう。
