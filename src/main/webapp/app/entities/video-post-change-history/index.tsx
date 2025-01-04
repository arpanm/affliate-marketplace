import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoPostChangeHistory from './video-post-change-history';
import VideoPostChangeHistoryDetail from './video-post-change-history-detail';
import VideoPostChangeHistoryUpdate from './video-post-change-history-update';
import VideoPostChangeHistoryDeleteDialog from './video-post-change-history-delete-dialog';

const VideoPostChangeHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<VideoPostChangeHistory />} />
    <Route path="new" element={<VideoPostChangeHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<VideoPostChangeHistoryDetail />} />
      <Route path="edit" element={<VideoPostChangeHistoryUpdate />} />
      <Route path="delete" element={<VideoPostChangeHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VideoPostChangeHistoryRoutes;
