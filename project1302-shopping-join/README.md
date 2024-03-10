== ハンズオン
=== 概要
RowMapper インタフェースを使用して、１対多の JOIN の取得結果をオブジェクトに変換します。

=== 手順
. JdbcOrderItemRepository クラスの中身を見てみましょう。selectById メソッドが実装されてはいますが、JOIN したレコードのデータが上手くオブジェクト(OrderItem オブジェクトが Product オブジェクトを保持した状態)にマッピングされるでしょうか？

. 上手くオブジェクトにマッピングされるように修正しましょう。RowMapper インタフェースを実装したクラスの作成が必要です。修正出来たら、TrainingApplication クラスの main メソッドの中で、selectById メソッドを呼び出してオブジェクトの中身を確認しましょう。

=== オプション

. JdbcOrderRepository クラスの selectById メソッドで、１対多の関係のオブジェクト(Order オブジェクトが複数の OrderItem オブジェクトを保持した状態)にマッピングするように修正しましょう。
.. 【補足】PaymentMethod は enum 型ですが、JDBC は enum 型の変換に対応していません。ですので、「rs.getObject("カラム名", PaymentMethod.class)」という記述ができません。代わりに、「PaymentMethod.valueOf(rs.getString("カラム名"))」と記述すればよいです。
