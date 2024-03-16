== ハンズオン
=== 概要
Service のユニットテストを作成します。

=== 手順
. ProductMaintenanceServiceImplTest クラスの中身を見てみましょう。update メソッドのテストメソッドが存在します。
. 以下のメソッドのテストメソッドを作成しましょう。
.. findAll メソッド
... 最低限、取得件数が期待通りになっていることを確認しましょう。
.. findById メソッド
... 適当な ID で検索し、Product オブジェクトの中身が期待通りか確認しましょう。

. update メソッドのテストメソッドでは、Mock の ProductRepository の update メソッドが呼ばれたことは確認できていますが、メソッドの引数で渡された Product オブジェクトのフィールドの値が正しいか否かは確認できていません。Mockito の ArgumentCaptor クラスを使って確認しましょう。

=== オプション
. OrderServiceImplTest の test_placeOrder メソッドでも、Mock のメソッドの引数で渡されたオブジェクトに対して、フィールドの値が正しいかを確認できていません。ArgumentCaptor クラスを使って確認しましょう。なお、Mock の同じメソッドが複数回呼び出された場合、ArgumentCaptor の getAllValues メソッドを使って、呼び出された数分の引数のオブジェクトを List 型のオブジェクトで取得できます。
