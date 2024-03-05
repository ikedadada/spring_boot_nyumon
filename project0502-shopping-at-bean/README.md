== ハンズオン

=== 概要
ライブラリのクラスを@Bean メソッドで Bean 定義します。

=== 手順
. ShoppingApplication クラスの main メソッドを実行してみましょう。「No qualifying bean of type 'javax.sql.DataSource' available」というエラーが発生します。原因は何でしょうか？

. ShoppingApplication クラスに以下のソースコードを追加してもう一度実行してみましょう(ソースコードを張り付けた後、統合開発環境の機能を使って import 文を補完してください)。上手く動くはずです。何が起こったか説明できますか？

- ***
      @Bean
      public DataSource dataSource() {
          EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
                  .addScripts("schema.sql", "data.sql")
                  .setType(EmbeddedDatabaseType.H2).build();
          return dataSource;
      }
  ***

=== オプション
. Service と Repository の Bean 定義の手段を、@Bean メソッドに変更してみましょう。
