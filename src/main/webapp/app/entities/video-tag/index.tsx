import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoTag from './video-tag';
import VideoTagDetail from './video-tag-detail';
import VideoTagUpdate from './video-tag-update';
import VideoTagDeleteDialog from './video-tag-delete-dialog';

const VideoTagRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<VideoTag />} />
    <Route path="new" element={<VideoTagUpdate />} />
    <Route path=":id">
      <Route index element={<VideoTagDetail />} />
      <Route path="edit" element={<VideoTagUpdate />} />
      <Route path="delete" element={<VideoTagDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VideoTagRoutes;
