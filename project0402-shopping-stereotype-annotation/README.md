== ハンズオン

=== 概要
既存のプログラムにステレオタイプアノテーションを記述して、Bean 定義を行います。合わせて、インジェクションするためのアノテーションも確認します。

=== 手順
. ShoppingApplication クラスの main メソッドを実行してみましょう。「No qualifying bean of type 'com.example.shopping.service.OrderService' available」というエラーが出力されるはずです。これは、main メソッドの中で getBean メソッドを呼び出した際に、指定した OrderService 型に該当する Bean が存在しないため DI コンテナが例外をスローしたためです。また、現在は OrderService 型の Bean 以外にも、OrderRepository 型の Bean、OrderItemRepository 型の Bean、ProductRepository 型の Bean も存在していない状態です。

. OrderService 型の Bean、OrderRepository 型の Bean、OrderItemRepository 型の Bean、ProductRepository 型の Bean を DI コンテナに登録するように修正してください。
.. ヒントは、ステレオタイプアノテーションとコンポーネントスキャンです。

. 再度 ShoppingApplication クラスの main メソッドを実行し、「注文確定処理が完了しました。・・・」という文言がコンソール画面に出力されれば OK です。

=== オプション

. Repository 型のオブジェクトのインジェクションの方法を、Setter ジェクションやフィールドインジェクションに変えてみましょう。

. 記載したアノテーションを部分的にわざと削除して、実行時にどのようなエラーがでるか試してみましょう。
