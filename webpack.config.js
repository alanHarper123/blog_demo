
var path = require('path');
var node_dir = __dirname + '/node_modules';
var webpack = require('webpack');
var ManifestPlugin = require('webpack-manifest-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

const frontEndBuildConfig = {

		watchOptions: {
			aggregateTimeout: 300,
			poll: 1000
		},
		mode: 'development',
		entry: {
			like_button: path.resolve('./src/main/resources/static/js/react/like_button.js'),
			comment_block: path.resolve('./src/main/resources/static/js/react/comment_block.js'),
			comment_block_001: path.resolve('./src/main/resources/static/js/react/comment_block_001.js'),
		},
		devtool: 'source-map',
		cache: true,
		resolve: {
			alias: {
				'stompjs': node_dir + '/stompjs/lib/stomp.js',
			}
		},
		
		plugins: [
			new webpack.LoaderOptionsPlugin({
				debug: true
			}),
			new ManifestPlugin(),
			new CleanWebpackPlugin(["src/main/resources/static/js/built"],{exclude:["*.json"]})
			],
			module: {
				rules: [
					{
						test: /\.css$/,
						exclude: /(node_modules)/,
						use: [ 'style-loader', 'css-loader' ]

					},
					{
						test: /\.(js|jsx)$/,
						exclude: /(node_modules)/,
						use: {
							loader: 'babel-loader',
							options: {
								presets: ['@babel/preset-env', '@babel/preset-react']
							}
						}

					}
					]
			}		
};
const outputOne = Object.assign({},frontEndBuildConfig,{
	output: {
		path: __dirname+'/src/main/resources/static/js/built/',
		filename: '[name].[hash].js'
	},
});

const outputTwo = Object.assign({},frontEndBuildConfig,{
	output: {
		path: __dirname+'/target/classes/resources/static/js/built/',
		filename: '[name].[hash].js'
	},
});
module.exports = [outputOne, outputTwo];