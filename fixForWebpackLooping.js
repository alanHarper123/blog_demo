var webpack = require('webpack');
const webpackConfig = require('./webpack.config.js')
const compiler = webpack(webpackConfig);
console.log("fix for loopings");
// return compiler.watch({aggregateTimeout:3000, poll: 1000}, (err, stats) => {
//   // Print watch/build result here...
//   console.log(err);
//   console.log(stats);
// });

compiler.run((err, stats) => {console.log(stats.hash)});
