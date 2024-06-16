SUMMARY = "ONNX Runtime recipe"
HOMEPAGE = "https://onnxruntime.ai/"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0f7e3b1308cb5c00b372a6e78835732d"

SRCREV = "45737400a2f3015c11f005ed7603611eaed306a6"

SRC_URI = " \
    git://github.com/microsoft/onnxruntime.git;branch=rel-1.18.0;protocol=https \
    file://0001-fix_requirements.txt.patch \
    file://0001-modify_platform_cpp.patch \
    file://0001-remove-onnxruntime_test.patch \
    file://0001-fix-tree_ensemble_aggregator-template-id-cdtor.patch \
"

S = "${WORKDIR}/git"

PACKAGECONFIG ?= "crosscompiling sharedlib python"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'python', 'setuptools3', '', d)}

inherit cmake

OECMAKE_SOURCEPATH = "${S}/cmake"

EXTRA_OECMAKE += "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=OFF \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -Donnxruntime_BUILD_UNIT_TESTS=ON \
"

PYTHON_DEPENDS = "\
    ${PYTHON_PN} \
    ${PYTHON_PN}-pip-native \
    ${PYTHON_PN}-numpy-native \
    ${PYTHON_PN}-packaging-native\
    ${PYTHON_PN}-pybind11\
    ${PYTHON_PN}-pybind11-native\
"

PYTHON_RDEPENDS = "\
    ${PYTHON_PN}-numpy \
    ${PYTHON_PN}-protobuf \
    ${PYTHON_PN}-coloredlogs \
    ${PYTHON_PN}-flatbuffers \
    ${PYTHON_PN}-sympy \
"

PACKAGECONFIG[armnn-bn] = "-Donnxruntime_ARMNN_BN_USE_CPU=ON, -Donnxruntime_ARMNN_BN_USE_CPU=OFF"
PACKAGECONFIG[armnn-relu] = "-Donnxruntime_ARMNN_RELU_USE_CPU=ON, -Donnxruntime_ARMNN_RELU_USE_CPU=OFF"
PACKAGECONFIG[automl] = "-Donnxruntime_USE_AUTOML=ON, -Donnxruntime_USE_AUTOML=OFF"
PACKAGECONFIG[brainslice] = "-Donnxruntime_USE_BRAINSLICE=ON, -Donnxruntime_USE_BRAINSLICE=OFF"
PACKAGECONFIG[crosscompiling] = "-Donnxruntime_CROSS_COMPILING=ON, -Donnxruntime_CROSS_COMPILING=OFF "
PACKAGECONFIG[csharp] = "-Donnxruntime_BUILD_CSHARP=ON, -Donnxruntime_BUILD_CSHARP=OFF"
PACKAGECONFIG[cuda] = "-Donnxruntime_USE_CUDA=ON, -Donnxruntime_USE_CUDA=OFF"
PACKAGECONFIG[devmode] = "-Donnxruntime_DEV_MODE=ON, -Donnxruntime_DEV_MODE=OFF"
PACKAGECONFIG[dml] = "-Donnxruntime_USE_DML=ON, -Donnxruntime_USE_DML=OFF"
PACKAGECONFIG[dnnl] = "-Donnxruntime_USE_DNNL=ON, -Donnxruntime_USE_DNNL=OFF"
PACKAGECONFIG[eigenblas] = "-Donnxruntime_USE_EIGEN_FOR_BLAS=ON, -Donnxruntime_USE_EIGEN_FOR_BLAS=OFF"
PACKAGECONFIG[eigenthreadpool] = "-Donnxruntime_USE_EIGEN_THREADPOOL=ON, -Donnxruntime_USE_EIGEN_THREADPOOL=OFF"
PACKAGECONFIG[fullprotobuf] = "-Donnxruntime_USE_FULL_PROTOBUF=ON, -Donnxruntime_USE_FULL_PROTOBUF=OFF"
PACKAGECONFIG[gemmlowp] = "-Donnxruntime_USE_GEMMLOWP=ON, -Donnxruntime_USE_GEMMLOWP=OFF"
PACKAGECONFIG[interop] = "-Donnxruntime_ENABLE_LANGUAGE_INTEROP_OPS=ON, -Donnxruntime_ENABLE_LANGUAGE_INTEROP_OPS=OFF"
PACKAGECONFIG[java] = "-Donnxruntime_BUILD_JAVA=ON, -Donnxruntime_BUILD_JAVA=OFF"
PACKAGECONFIG[jemalloc] = "-Donnxruntime_USE_JEMALLOC=ON, -Donnxruntime_USE_JEMALLOC=OFF"
PACKAGECONFIG[llvm] = "-Donnxruntime_USE_LLVM=ON, -Donnxruntime_USE_LLVM=OFF"
PACKAGECONFIG[microsoft] = "-Donnxruntime_ENABLE_MICROSOFT_INTERNAL=ON, -Donnxruntime_ENABLE_MICROSOFT_INTERNAL=OFF"
PACKAGECONFIG[mimalloc] = "-Donnxruntime_USE_MIMALLOC=ON, -Donnxruntime_USE_MIMALLOC=OFF"
PACKAGECONFIG[mklml] = "-Donnxruntime_USE_MKLML=ON, -Donnxruntime_USE_MKLML=OFF"
PACKAGECONFIG[ngraph] = "-Donnxruntime_USE_NGRAPH=ON, -Donnxruntime_USE_NGRAPH=OFF"
PACKAGECONFIG[nnapi] = "-Donnxruntime_USE_NNAPI_BUILTIN=ON, -Donnxruntime_USE_NNAPI_BUILTIN=OFF"
PACKAGECONFIG[nsync] = "-Donnxruntime_USE_NSYNC=ON, -Donnxruntime_USE_NSYNC=OFF"
PACKAGECONFIG[nuphar] = "-Donnxruntime_USE_NUPHAR=ON, -Donnxruntime_USE_NUPHAR=OFF"
PACKAGECONFIG[openblas] = "-Donnxruntime_USE_OPENBLAS=ON, -Donnxruntime_USE_OPENBLAS=OFF"
PACKAGECONFIG[openmp] = "-Donnxruntime_USE_OPENMP=ON, -Donnxruntime_USE_OPENMP=OFF"
PACKAGECONFIG[openvino] = "-Donnxruntime_USE_OPENVINO=ON, -Donnxruntime_USE_OPENVINO=OFF"
PACKAGECONFIG[ops] = "-Donnxruntime_DISABLE_CONTRIB_OPS=ON, -Donnxruntime_DISABLE_CONTRIB_OPS=OFF"
PACKAGECONFIG[opschema] = "-Donnxruntime_PYBIND_EXPORT_OPSCHEMA=ON, -Donnxruntime_PYBIND_EXPORT_OPSCHEMA=OFF"
PACKAGECONFIG[prebuilt] = "-Donnxruntime_USE_PREBUILT_PB=ON, -Donnxruntime_USE_PREBUILT_PB=OFF"
PACKAGECONFIG[python] = "-Donnxruntime_ENABLE_PYTHON=ON, -Donnxruntime_ENABLE_PYTHON=OFF, ${PYTHON_DEPENDS}, ${PYTHON_RDEPENDS}"
PACKAGECONFIG[reports] = "-Donnxruntime_GENERATE_TEST_REPORTS=ON, -Donnxruntime_GENERATE_TEST_REPORTS=OFF"
PACKAGECONFIG[runtests] = "-Donnxruntime_RUN_ONNX_TESTS=ON, -Donnxruntime_RUN_ONNX_TESTS=OFF"
PACKAGECONFIG[server] = "-Donnxruntime_BUILD_SERVER=ON, -Donnxruntime_BUILD_SERVER=OFF"
PACKAGECONFIG[sharedlib] = "-Donnxruntime_BUILD_SHARED_LIB=ON, -Donnxruntime_BUILD_SHARED_LIB=OFF"
PACKAGECONFIG[staticruntime] = "-Donnxruntime_MSVC_STATIC_RUNTIME=ON, -Donnxruntime_MSVC_STATIC_RUNTIME=OFF"
PACKAGECONFIG[telemetry] = "-Donnxruntime_USE_TELEMETRY=ON, -Donnxruntime_USE_TELEMETRY=OFF"
PACKAGECONFIG[tensorrt] = "-Donnxruntime_USE_TENSORRT=ON, -Donnxruntime_USE_TENSORRT=OFF"
PACKAGECONFIG[trt] = "-Donnxruntime_USE_TRT=ON, -Donnxruntime_USE_TRT=OFF"
PACKAGECONFIG[tvm] = "-Donnxruntime_USE_TVM=ON, -Donnxruntime_USE_TVM=OFF"
PACKAGECONFIG[x86] = "-Donnxruntime_BUILD:x86=ON, -Donnxruntime_BUILD:x86=OFF"

do_configure[network] = "1"

do_install:append() {
    CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"

    # Ensure target dir exists
    install -d ${D}${bindir}/${BP}


    # If cmake installs 'onnx_test_runner' at bindir level, move to package
    if [ -f ${D}${bindir}/onnx_test_runner ]; then
        mv ${D}${bindir}/onnx_test_runner ${D}${bindir}/${BP}/
    fi

    # Install onnxruntime_perf_test in main package
    install -m 0755 ${B}/onnxruntime_perf_test ${D}${bindir}/${BP}

    # Install test binaries and data in test package
    install -d ${D}${bindir}/${BP}/tests
    install -m 0744 ${B}/libcustom_op_library.so ${D}${bindir}/${BP}/tests
    install -m 0744 ${B}/onnxruntime_global_thread_pools_test ${D}${bindir}/${BP}/tests
    install -m 0744 ${B}/onnxruntime_mlas_test ${D}${bindir}/${BP}/tests
    install -m 0744 ${B}/onnxruntime_shared_lib_test ${D}${bindir}/${BP}/tests
    install -m 0744 ${B}/onnxruntime_test_all ${D}${bindir}/${BP}/tests
    cp $CP_ARGS ${B}/testdata ${D}${bindir}/${BP}/tests

    if ${@bb.utils.contains('PACKAGECONFIG', 'python', 'true', 'false', d)}; then
        setuptools3_do_install
        find ${D}/${PYTHON_SITEPACKAGES_DIR} -type d -name "__pycache__" -exec rm -Rf {} +
        git config --global --unset-all safe.directory ${TMPDIR}/.*/${PN}/.*/build/pybind11/src/pybind11
    fi
}

# Adjust the Python runtime dependency inherited from setuptools3-base.bbclass
# since Python support for this recipe is conditional
RDEPENDS:${PN}:remove:class-target = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'python', '', '${PYTHON_PN}-core', d)}"

# libonnxruntime_providers_shared.so is being packaged into -dev which is intended
INSANE_SKIP:${PN}-dev += "dev-elf"

# libcustom_op_library.so is in bindir, which is intended;
# onnxruntime_shared_lib_test requires the shlib to be in the same directory as testdata to run properly
INSANE_SKIP:${PN}-tests += "libdir"
INSANE_SKIP:${PN}-dbg += "libdir"